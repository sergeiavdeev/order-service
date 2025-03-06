mvn clean package -Dmaven.test.skip=true
docker ps -aq --filter "name=order-service" | xargs -r docker rm -f
docker system prune -a -f
docker build -t sergeiavdeev/order-service:1-dev .
PROFILE=dev TAG=1-dev docker compose -f docker-compose-local.yml up -d
docker system prune -a -f