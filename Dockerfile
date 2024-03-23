FROM python:3.8-slim

RUN mkdir /location_project

WORKDIR /location_project

COPY requirements.txt .

RUN pip install --upgrade pip

RUN pip install --upgrade setuptools

RUN pip install -r requirements.txt

COPY . .

WORKDIR api

CMD gunicorn main:app --workers 1 --worker-class uvicorn.workers.UvicornWorker --bind=0.0.0.0:8000