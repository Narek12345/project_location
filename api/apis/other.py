from fastapi import APIRouter
from core.database import RegistrationCode

router = APIRouter()


@router.post("/add_registration_code")
async def add_registration_code(registration_code: int):
	"""Получаем registration_code и сохраняем в БД."""
	RegistrationCode.add_registration_code(registration_code)
	return {"success": True, "message": "Код добавлен"}