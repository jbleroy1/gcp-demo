docker build -t builder-native .
docker tag builder-native europe-west2-docker.pkg.dev/meta-yen-311209/docker-registry/builder-quarkus
docker push europe-west2-docker.pkg.dev/meta-yen-311209/docker-registry/builder-quarkus
