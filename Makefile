setup:
	docker compose build --no-cache

start:
	docker compose up --build -d

first_setup:
	docker compose build --no-cache && docker compose up --build -d

stop:
	docker compose down