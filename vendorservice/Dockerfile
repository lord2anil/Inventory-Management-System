FROM public.ecr.aws/docker/library/tomcat:8.5.68-jdk8-openjdk
WORKDIR /
COPY /target/project.war ./app.war
RUN ls -ltra
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.war"]
