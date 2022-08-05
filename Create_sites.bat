docker-compose up -d
cd patient 
call mvn verify site
cd ..\note
call mvn verify site
cd ..\assessment
call mvn verify site
cd ..