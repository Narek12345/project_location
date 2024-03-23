import random
from typing import Dict
from fastapi import APIRouter
from fastapi import UploadFile
from fastapi import HTTPException
from api.models.user_model import UserModel
from api.core.database import User, RegistrationCode

router = APIRouter()


@router.post('/register')
async def register(fio: str, age: int, city: str, address: str, registration_code: int, file: UploadFile) -> Dict:
	"""
	Регистрация пользователя.

	user_data: персональные данные пользователя.
	file: файл пользователя.

	return: Dict - словарь с ответами на запрос.
	"""
	# Генерация user_id.
	user_id = random.randint(10000000, 99999999)

	# Проверяем, есть ли переданный registration_code в БД.
	code = registration_code
	if RegistrationCode.check_registration_code(code):
		# Регистрируем пользователя.
		user = User.register(user_id, fio=fio, age=age, city=city, address=address, image=file.file.read())
		# Удаление registration_code из БД.
		RegistrationCode.delete_code(registration_code)
		return {"status_code": 200, "result": True, "message": "Пользователь успешно зарегестрировался"}
	else:
		# Такой registration_code не найден. Отправляем ошибку.
		return {"status_code": 400, "result": False, "message": "Регистрационный код не найден."}


@router.post("/login")
async def login(user_id: int):
	"""Вход в свою учетную запись."""
	login = User.login(user_id)
	if login:
		# Пользователь вошел. Отправляем соответствующий ответ
		return {"success": True, "message": "Пользователь успешно вошел"}
	else:
		# Пользователь не смог войти.
		return HTTPException(status_code=401, detail="Пользователь не авторизован")
