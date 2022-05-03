curl -X GET localhost:8080/screenings^
   -H "Content-Type: application/json"^
   -d "{\"yyyyMMDD\": \"20220501\", \"startHHMM\": \"0000\", \"endHHMM\": \"2359\"}"

curl -X GET localhost:8080/screening/6

curl -X GET localhost:8080/booking/6^
    -H "Content-Type: application/json"^
    -d "{\"seatSelectionRequests\": [{\"line\": 2, \"column\": 2, \"ticket\": \"adult\"}], \"firstName\": \"Kamil\", \"lastName\": \"Szydlowski\"}"
