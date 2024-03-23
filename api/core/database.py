from sqlalchemy import create_engine, Column, Integer, String, LargeBinary
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker
from .config import SQLALCHEMY_DATABASE_URL


# engine = create_engine(SQLALCHEMY_DATABASE_URL)
engine = create_engine(SQLALCHEMY_DATABASE_URL, pool_size=10, max_overflow=2, pool_recycle=300, pool_pre_ping=True, pool_use_lifo=True)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()


class User(Base):
	__tablename__ = "users"
	id = Column(Integer, primary_key=True)
	user_id = Column(Integer)
	fio = Column(String)
	age = Column(String)
	city = Column(String)
	address = Column(String)
	image = Column(LargeBinary)


	def register(user_id, fio, age, city, address, image):
		"""Регистрация пользователя."""
		db = SessionLocal()
		new_user = User(user_id=user_id, fio=fio, age=age, city=city, address=address, image=image)
		db.add(new_user)
		db.commit()
		db.refresh(new_user)
		return new_user


	def login(user_id):
		"""Вход в свою учетную запись."""
		db = SessionLocal()
		user = db.query(User).filter(User.user_id == user_id).first()

		if user:
			return True
		else:
			return False


class RegistrationCode(Base):
	__tablename__ = "registration_code"
	id = Column(Integer, primary_key=True)
	code = Column(Integer)


	def add_registration_code(registration_code):
		"""Добавление registration_code в БД."""
		db = SessionLocal()
		new_code = RegistrationCode(code=registration_code)
		db.add(new_code)
		db.commit()


	def check_registration_code(registration_code):
		"""Проверка, есть ли переданный код в БД. Если есть - True, если нет - False."""
		db = SessionLocal()
		existing_code = db.query(RegistrationCode).filter(RegistrationCode.code == registration_code).first()
		
		if existing_code:
			return True
		else:
			return False


	def delete_code(registration_code):
		"""Удаляет полученный registration_code из БД."""
		db = SessionLocal()
		existing_code = db.query(RegistrationCode).filter(RegistrationCode.code == registration_code).first()
		db.delete(existing_code)
		db.commit()
