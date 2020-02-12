docker-compose down
mvn clean package
docker image build -t bet-engine:latest .
docker-compose up
