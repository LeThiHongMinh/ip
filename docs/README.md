# Neura Task Manager User Guide

Welcome to **Neura Task Manager**, your personal task management assistant! With Neura, you can easily manage your tasks, set deadlines, and track events. This guide will walk you through all the features and how to use them.

## Quick Start

Ensure you have **Java 17** or above installed on your system.

### Steps to Get Started:
1. Download the latest `.jar` file from the [Neura GitHub repository](https://github.com/LeThiHongMinh/ip).
2. Copy the `.jar` file to the folder where you want to store your task manager.
3. Open a command terminal, navigate to the folder, and run the following command:
4. The program will start, and you can begin adding tasks, deadlines, and events.

### Example Commands:
- `todo Buy groceries` – Adds a simple to-do task.
- `deadline Submit report /by 2025-03-07 18:00` – Adds a task with a deadline.
- `event Team meeting /at 2025-03-09 14:00` – Adds an event task.

## Features

### Add To-Do: `todo`
Adds a to-do task to your task list. A to-do task is a simple task that doesn't have a deadline or specific event time.

- **Format**: `todo DESCRIPTION`
- **Example**:
- `todo Buy groceries`
- `todo Finish homework`

---

### Add Deadline: `deadline`
Adds a deadline task to your task list. A deadline task is associated with a due date and time by which the task must be completed.

- **Format**: `deadline DESCRIPTION /by DATE_TIME`
- **Example**:
- `deadline Submit report /by 2025-03-07 18:00`
- `deadline Pay bills /by 2025-03-10 12:00`

---

### Add Event: `event`
Adds an event task to your task list. An event task is associated with a specific date and time when the event will occur.

- **Format**: `event DESCRIPTION /at DATE_TIME`
- **Example**:
- `event Doctor appointment /at 2025-03-08 09:00`
- `event Team meeting /at 2025-03-09 14:00`

---

### Mark Task as Done: `mark`
Marks a task as completed in your task list.

- **Format**: `mark INDEX`
- **Example**:
- `mark 1` – Marks the 1st task as done.

---

### Unmark Task as Not Done: `unmark`
Unmarks a task, changing its status back to "not done."

- **Format**: `unmark INDEX`
- **Example**:
- `unmark 1` – Unmarks the 1st task.

---

### Delete Task: `delete`
Removes a task from your task list.

- **Format**: `delete INDEX`
- **Example**:
- `delete 1` – Deletes the 1st task in the list.

---

### View All Tasks: `list`
Displays a list of all the tasks in your task list.

- **Format**: `list`
- **Example**:
- `list` – Shows all tasks.

---

### Find Task by Description: `find`
Searches for tasks whose descriptions contain the given keyword(s).

- **Format**: `find KEYWORD`
- **Example**:
- `find groceries` – Displays all tasks that contain "groceries."

---

### Exit Program: `bye`
Exits the program.

- **Format**: `bye`
- **Example**:
- `bye` – Closes the application.

---

## Saving the Data
Neura automatically saves all tasks to a file after any changes are made. You don’t need to manually save your tasks.

## Editing the Data File
The tasks are saved automatically in a JSON file located in the `data` folder. Advanced users can edit this file directly, but be cautious. Editing it improperly may cause the application to fail.

**Caution**: If the data file format becomes corrupted, Neura will discard all data on the next run. Make sure to back up the file before making any edits.

---

## Command Summary

| Action | Format, Examples                                                                                |
|--------|-------------------------------------------------------------------------------------------------|
| Add todo | `todo DESCRIPTION`<br>e.g., `add todo Buy groceries`                                            |
| Add deadline | `deadline DESCRIPTION /by DATE_TIME`<br>e.g., `add deadline Submit report /by 2025-03-07 18:00` |
| Add event | `event DESCRIPTION /at DATE_TIME`<br>e.g., `add event Doctor appointment /at 2025-03-08 09:00`  |
| Mark task | `mark INDEX`<br>e.g., `mark 1`                                                                  |
| Unmark task | `unmark INDEX`<br>e.g., `unmark 1`                                                              |
| Delete task | `delete INDEX`<br>e.g., `delete 1`                                                              |
| List tasks | `list`<br>e.g., `list`                                                                          |
| Find task | `find KEYWORD`<br>e.g., `find groceries`                                                        |
| Exit program | `bye`<br>e.g., `bye`                                                                            |
