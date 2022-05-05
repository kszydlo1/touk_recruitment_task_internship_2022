#!/bin/bash
set -x

curl -X GET localhost:8080/screenings \
   -H "Content-Type: application/json" \
   -d "{\"yyyyMMDD\": \"20220501\", \"startHHMM\": \"1430\", \"endHHMM\": \"1530\"}"
echo "\n"

curl -X GET localhost:8080/screening/6
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 2, \"column\": 1, \"ticket\": \"adult\"},
            {\"line\": 2, \"column\": 2, \"ticket\": \"child\"},
            {\"line\": 1, \"column\": 1, \"ticket\": \"student\"}
            ], \"firstName\": \"Kamil\", \"lastName\": \"Szydłowski\"}"
echo "\n"

curl -X GET localhost:8080/screening/6
echo "\n"