FROM tomcat
COPY /target/RemoteDataStoreService-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/myapp.war
CMD ["catalina.sh", "run"]
