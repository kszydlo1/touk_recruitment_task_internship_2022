#!/bin/bash
set -x

curl -X GET localhost:8080/screenings \
   -H "Content-Type: application/json" \
   -d "{\"yyyyMMDD\": \"20220505\", \"startHHMM\": \"0000\", \"endHHMM\": \"2359\"}"
echo "\n"

curl -X GET localhost:8080/screenings \
   -H "Content-Type: application/json" \
   -d "{\"yyyyMMDD\": \"20220505\", \"startHHMM\": \"1500\", \"endHHMM\": \"1400\"}"
echo "\n"

curl -X GET localhost:8080/screenings \
   -H "Content-Type: application/json" \
   -d "{\"yyyyMMDD\": \"20220501\", \"startHHMM\": \"0000\", \"endHHMM\": \"2359\"}"
echo "\n"

curl -X GET localhost:8080/screening/-1
echo "\n"

curl -X GET localhost:8080/screening/1
echo "\n"

curl -X GET localhost:8080/screening/100
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            
         ], 
         \"firstName\": \"Kamil\", \"lastName\": \"Szydłowski\"}"
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 2, \"column\": 1, \"ticket\": \"adult\"},
            {\"line\": 2, \"column\": 2, \"ticket\": \"child\"},
            {\"line\": 1, \"column\": 1, \"ticket\": \"student\"}
            ], \"firstName\": \"kamil\", \"lastName\": \"Szydłowski\"}"
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 2, \"column\": 1, \"ticket\": \"adult\"},
            {\"line\": 2, \"column\": 2, \"ticket\": \"child\"},
            {\"line\": 1, \"column\": 1, \"ticket\": \"student\"}
            ], \"firstName\": \"Kamil\", \"lastName\": \"szydłowski\"}"
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 2, \"column\": 1, \"ticket\": \"adult\"},
            {\"line\": 2, \"column\": 2, \"ticket\": \"child\"},
            {\"line\": 1, \"column\": 1, \"ticket\": \"student\"}
            ], \"firstName\": \"Kamil\", \"lastName\": \"Szydłowski-A\"}"
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 2, \"column\": 1, \"ticket\": \"adult\"},
            {\"line\": 2, \"column\": 2, \"ticket\": \"child\"},
            {\"line\": 1, \"column\": 1, \"ticket\": \"student\"}
            ], \"firstName\": \"Kamil\", \"lastName\": \"A-Szydłowski\"}"
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 2, \"column\": 1, \"ticket\": \"adult\"},
            {\"line\": 2, \"column\": 2, \"ticket\": \"child\"},
            {\"line\": 1, \"column\": 1, \"ticket\": \"student\"}
            ], \"firstName\": \"Kamil\", \"lastName\": \"aaa-Szydłowski\"}"
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 2, \"column\": 1, \"ticket\": \"adult\"},
            {\"line\": 2, \"column\": 2, \"ticket\": \"child\"},
            {\"line\": 1, \"column\": 1, \"ticket\": \"student\"}
            ], \"firstName\": \"Kamil\", \"lastName\": \"Szydłowski-aaa\"}"
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 2, \"column\": 1, \"ticket\": \"adult\"},
            {\"line\": 2, \"column\": 2, \"ticket\": \"child\"},
            {\"line\": 1, \"column\": 1, \"ticket\": \"student\"}
            ], \"firstName\": \"Kamil\", \"lastName\": \"Aaa-Baa-Caa\"}"
echo "\n"

curl -X GET localhost:8080/screening/6
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 2, \"column\": 1, \"ticket\": \"adult\"},
            {\"line\": 2, \"column\": 2, \"ticket\": \"child\"},
            {\"line\": 2, \"column\": 3, \"ticket\": \"student\"}
            ], \"firstName\": \"Kamil\", \"lastName\": \"Aaa-Baa\"}"
echo "\n"

curl -X GET localhost:8080/screening/6
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 1, \"column\": 1, \"ticket\": \"adult\"}
            ], \"firstName\": \"Kamil\", \"lastName\": \"Aaa-Baa\"}"
echo "\n"

curl -X GET localhost:8080/screening/6
echo "\n"

curl -X GET localhost:8080/booking/6 \
    -H "Content-Type: application/json" \
    -d "{\"seatSelectionRequests\": 
         [
            {\"line\": 1, \"column\": 3, \"ticket\": \"adult\"}
            ], \"firstName\": \"Kamil\", \"lastName\": \"Aaa-Baa\"}"
echo "\n"

curl -X GET localhost:8080/screening/6
echo "\n"