set -e

if [ "$1" = 'myapp' ]; then
    shift
    exec java -jar -D spring.profiles.active=$PROFILE /app.jar "$@"
fi

exec "$@"