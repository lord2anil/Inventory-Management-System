FROM public.ecr.aws/docker/library/tomcat:8.5.68-jdk8-openjdk
WORKDIR /
COPY /target/project-1.5.2.RELEASE.war ./app.war
RUN ls -ltra
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.war"]
