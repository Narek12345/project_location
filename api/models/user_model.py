from pydantic import BaseModel


class UserModel(BaseModel):
	fio: str
	age: int
	city: str
	address: str
	registration_code: int
