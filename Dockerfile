FROM python:3.10-slim

RUN mkdir /fastapi_app

WORKDIR /fastapi_app

COPY install_packages.sh .

RUN install_packages.sh

COPY . .

WORKDIR api

CMD gunicorn main:app --workers 1 --worker-class uvicorn.workers.UvicornWorker --bind=0.0.0.0:8000
