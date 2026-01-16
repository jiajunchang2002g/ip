# Jarvis

Jarvis is a task management application that helps you keep track of your todos, deadlines, and events. It provides a simple command-line interface to add, mark, unmark, delete, and list your tasks.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
1. After that, locate the `src/main/java/Jarvis.java` file, right-click it, and choose `Run Jarvis.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Welcome to Jarvis! How can I assist you today?
   ```

**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.

## Features

- **Add Todos**: Create simple tasks without deadlines.
- **Add Deadlines**: Create tasks with a specific due date.
- **Add Events**: Create events with start and end times.
- **Mark/Unmark Tasks**: Mark tasks as done or not done.
- **List Tasks**: View all your tasks.
- **Delete Tasks**: Remove tasks from your list.
- **Exit**: Say goodbye to Jarvis.

## Usage

### Adding a Todo
```
todo read book
```
Expected output:
```
Added ToDo: read book
```

### Adding a Deadline
```
deadline submit assignment /by 2023-10-01
```
Expected output:
```
Added Deadline: submit assignment (by: 2023-10-01)
```

### Adding an Event
```
event team meeting /from 2023-10-01 10:00 /to 2023-10-01 11:00
```
Expected output:
```
Added Event: team meeting (from: 2023-10-01 10:00 to: 2023-10-01 11:00)
```

### Listing Tasks
```
list
```
Expected output:
```
Here are your tasks:
1. [T][ ] read book
2. [D][ ] submit assignment (by: 2023-10-01)
3. [E][ ] team meeting (from: 2023-10-01 10:00 to: 2023-10-01 11:00)
```

### Marking a Task as Done
```
mark 1
```
Expected output:
```
Task marked as done.
```

### Unmarking a Task
```
unmark 1
```
Expected output:
```
Task marked as not done.
```

### Deleting a Task
```
delete 2
```
Expected output:
```
Deleted task: [D][ ] submit assignment (by: 2023-10-01)
```

### Exiting the Application
```
bye
```
Expected output:
```
Goodbye! Have a great day!
```
