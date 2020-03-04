# Algorithms
Algorithms, Data Structures and optimization problems implemented using Java

## Sorting
- Bubble Sort
- Insertion Sort
- Selection Sort
- Merge Sort
- Insertion sort with binary Search {_Can be found in **com.michael.search**_}

## Hashcode problems covered
- Hashcode 2017 practice round - {_The task description can be found in the resource folder_}
- Hashcode 2018 online Qualification Round (The solution will earn you 46,756,961)

## Sudoku Solver
The solution provided is for a 9 X 9 grid with the following rule. The numbers 1 - 9 should only appear once on the :
  1. Row
  2. Column 
  3. 3 X 3 grid

**(Approach used)**
- To solve it find an empty cell and try all the numbers from 1 - 9 and if any fits continue else **backtrack** by making the
cell empty. Continue doing this until all the cells are filled.

  
## Search
- Binary search

## Meeting Scheduler
The problem description can found in the folder _**com.michael.meetingScheduler**_ 
- Take the schedule of each person combine them into one List.
- Sort the List according to their starting time.
- If there is any overlap combine the intervals into one e.g {{"09:00", "10:30"} ,{"10:00","11:00"}, ... }
this 2 entries can be considered as one entry i.e {{"09:00","11:00"}, ... } since no meeting can be held in between them.
- Use the new formed list to find the times in which the meeting can be held, ensuring they fit in between the bounds of each
person.

        
