docker-compose up -d
cd patient 
call mvn verify
cd ..\note
call mvn verify
cd ..\assessment
call mvn verify
docker-compose down