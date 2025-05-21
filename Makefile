.PHONY: build test docker_build docker_deploy


build:
	mvn -s .circleci/settings.xml  clean package -DskipTests=true

test:
	mvn -s .circleci/settings.xml  test

deploy:
	mvn -s .circleci/settings.xml  deploy