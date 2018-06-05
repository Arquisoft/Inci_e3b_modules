@echo off
REM Stop all containers: 
docker ps
FOR /f "tokens=*" %%i IN ('docker ps -aq') DO docker container stop %%i 
REM Remove all container:
docker container ls
FOR /f "tokens=*" %%i IN ('docker ps -aq') DO docker container rm %%i 
REM Remove all volumes:
docker volume ls
FOR /f "tokens=*" %%i IN ('docker volume ls -q') DO docker volume rm %%i 
REM Remove all networks:
docker network ls
FOR /f "tokens=*" %%i IN ('docker network ls -q') DO docker network rm %%i 
REM Remove all images:
docker image ls
REM FOR /f "tokens=*" %%i IN ('docker images --format "{{.ID}}"') DO docker rmi %%i
pause
