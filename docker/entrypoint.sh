docker-compose -p nginx -f docker-compose.nginx.yml down 
docker-compose -p nginx -f docker-compose.nginx.yml up -d

cd /home/myapp/docker

# 변수 선언
APP_NAME=meetup # App 이름
COMPOSE_FILE_NAME=docker_compose/docker-compose #docker compose conf 위치
# 현재 blue 컨테이너가 띄워져 있는 지에 대한 여부
IS_RUN_BLUE=$(docker-compose -p ${APP_NAME}-blue -f ${COMPOSE_FILE_NAME}.blue.yaml ps | grep Up)
# time out 시 에러 발생
TIME_OUT=60
# WAS Test API
TEST_API=http://localhost:1323/api/petbuddy/v1/test


# 도커 이미지 빌드
docker build --platform linux/amd64 --no-cache=true --pull=true -t ${APP_NAME}:latest -f docker_file/prod.Dockerfile . | exit 1

# 기존 컨테이너, 새로운 컨테이너 color 셋팅
if [ -n "$IS_RUN_BLUE" ]; then
  BEFORE_COLOR="blue"
  AFTER_COLOR="green"
else
  BEFORE_COLOR="green"
  AFTER_COLOR="blue"
fi

# 새로운 컨테이너가 정상적으로 띄워질 때까지 기다림(loop) - WAS API 테스트 포함
echo "${AFTER_COLOR} container up"
docker-compose -p ${APP_NAME}-${AFTER_COLOR} -f ${COMPOSE_FILE_NAME}.${AFTER_COLOR}.yaml up -d || exit 1

RUNNING_TIME=0
while [ 1 == 1 ]
do
  START_TIME=`date +%s`
  sleep 1
  # container 띄워졌는지 확인
  IS_UP_AFTER=$(docker-compose -p ${APP_NAME}-${AFTER_COLOR} -f ${COMPOSE_FILE_NAME}.${AFTER_COLOR}.yaml ps | grep Up)
  if [ -n "$IS_UP_AFTER" ]; then
    # 컨테이너 내부 WAS 띄워졌는지 확인
    TEST_API_STATUS_CODE=$(docker exec ${APP_NAME}-${AFTER_COLOR} curl -o /dev/null -w "%{http_code}" "${TEST_API}")
    if [ "$TEST_API_STATUS_CODE" == 200 ]; then
      echo "TEST API SUCCESS !! >> ${AFTER_COLOR} Container WAS Running!"
      break
    fi
  fi

  END_TIME=`date +%s`
  TIME_DIFF=`echo "$END_TIME - $START_TIME" | bc -l`
  # 걸린 시간 check
  RUNNING_TIME=$(($RUNNING_TIME + $TIME_DIFF))

  # timeout 시 에러 발생
  if [ $RUNNING_TIME -gt $TIME_OUT ]; then
    echo "ERROR TIMEOUT!!"
    exit 1
  fi
done

# 새로운 컨테이너가 띄워졌다면, nginx conf 변경 후 nginx reload
cp nginx_conf/${APP_NAME}-${AFTER_COLOR}.conf /etc/nginx/conf.d/petbuddy.conf || exit 1
nginx -s reload || exit 1

# 기존 컨테이너 down
docker-compose -p ${APP_NAME}-${BEFORE_COLOR} -f ${COMPOSE_FILE_NAME}.${BEFORE_COLOR}.yaml down || exit 1
echo "${BEFORE_COLOR} container down"