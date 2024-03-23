from fastapi import APIRouter
from api.apis.user import router as user_router

router = APIRouter()

router.include_router(user_router, prefix="/api")
