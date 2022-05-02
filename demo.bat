curl -X GET localhost:8080/screenings^
   -H "Content-Type: application/json"^
   -d "{\"yyyyMMDD\": \"20220501\", \"startHHMM\": \"1400\", \"endHHMM\": \"1630\"}"