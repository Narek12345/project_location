from fastapi import APIRouter
from apis.other import router as other_router

router = APIRouter()

router.include_router(other_router, prefix="/api")
