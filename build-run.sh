echo "--- compile and package the application as an executable JAR ---"
mvn clean package

echo "--- build the image ---"
docker image build -t bet-engine:latest .

echo "--- list all your local images ---"
docker image ls

echo "--- see a breakdown of the various layers in the image. ---"
docker image history bet-engine

echo "-- run a container and expose port ---"
docker container run -p 8088:8088 bet-engine

#docker
