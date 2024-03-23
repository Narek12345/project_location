from fastapi import FastAPI
from api.core.database import Base, engine
from api.routers import user_router, other_router

app = FastAPI()

# Создание таблицы
Base.metadata.create_all(bind=engine)

# Регистрация путей эндпоинтов.
app.include_router(user_router.router)
app.include_router(other_router.router)
