FROM gradle

COPY . .

EXPOSE 80

CMD ["./gradlew", "review:bootRun"]
