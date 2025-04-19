# 📝 Task Manager App

A modern Task Manager Android application built using **Kotlin**, **Jetpack Compose**, **Room**, **Koin**, and **MVVM architecture**. Users can create, update, delete, and manage tasks with different priorities, sort and filter them, and enjoy both light and dark themes with a custom switch.

## ✨ Features

- ✅ **Add Tasks**: User can add a task with a title, description, and select a priority (Low, Medium, High).
- 🗂️ **Task Priority**: Tasks can be marked with Low, Medium, or High priority.
- 🔄 **Mark as Completed**: Tasks can be marked as completed or pending.
- 🔍 **Filter Tasks**:
  - View **All**, **Completed**, or **Pending** tasks.
- 🧮 **Sort Tasks**:
  - By **Title**
  - By **Date**
  - By **Priority**
- 🧭 **Navigation**:
  - **Home Screen**: Displays all tasks and allows filtering/sorting.
  - **Create Task Screen**: Allows the user to enter title, description, and priority.
  - **Task Details Screen**: View task details and edit or delete the task.
- 🎨 **Light/Dark Mode**: Supports both modes with a **custom theme switch**.
- 🗑️ **Delete & Undo**: When a task is deleted, a **Snackbar with Undo option** appears to restore the task if needed.
- ✨ **Shimmer Effect**: Smooth loading indicator while data is being fetched.
- 🧭 **Dynamic Top Bar**: The **top app bar hides or shows based on scroll direction** for a cleaner user experience.


## Architecture block diagram
![Android Architecture](https://github.com/lofcoding/AndroidArchitectureSample/assets/109604722/ed29d956-1154-4518-9107-e4e1a34b4a35)

## 📲 User Flow

1. User opens the app.
2. On the **Home Screen**, they tap the **FAB (Add)** button.
3. Navigated to **Create Task Screen**.
4. User enters the **Title**, **Description**, selects **Priority**, and clicks **Save**.
5. Task appears on the **Home Screen**, filtered by current selection (All, Completed, Pending).
6. User can **mark task as complete**, **sort** by title/date/priority, or navigate to **task details** to edit/delete.
7. On delete, a **Snackbar** shows with **Undo** action.

## 🛠️ Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM
- **Local Storage**: Room Database
- **Dependency Injection**: Koin
- **Testing**: Compose UI Testing
- **Effects**: Shimmer loading animation
- **Theme**: Light and Dark mode with a custom switch
- **UX Enhancements**: Top bar visibility on scroll

## 📸 Screenshots

<table>
  <tr> 
    <td><img src="https://github.com/user-attachments/assets/9bfca03c-e70d-4d2a-b40a-b6a42d640006" alt="splash" width="240"></td>
    <td><img src="https://github.com/user-attachments/assets/74c3c14e-ed8e-4daf-a166-9f8fbe5986cb" alt="splash" width="240"></td>
    <td><img src="https://github.com/user-attachments/assets/e8f0f950-5f14-4908-92af-20f009cacd7e" alt="splash" width="240"></td>
     
  </tr>
    <tr>
      <td><img src="https://github.com/user-attachments/assets/b6432a1c-7041-468a-9c78-57045ab11fa0" alt="splash" width="240"></td>
     <td><img src="https://github.com/user-attachments/assets/5908368a-1549-4856-a285-b9a7b3270e39" alt="splash" width="240"></td>
      <td><img src="https://github.com/user-attachments/assets/a641a36c-628b-46f9-94ae-ed59a669f809" alt="splash" width="240"></td>
  </tr>
    <tr>
      <td><img src="https://github.com/user-attachments/assets/f5516716-43f8-46fd-a75a-b40c70b69012" alt="splash" width="240"></td>
      <td><img src="https://github.com/user-attachments/assets/0d60459b-6461-461e-a689-325ea5fe7101" alt="splash" width="240"></td>
      <td><img src="https://github.com/user-attachments/assets/d4e2e61c-abc8-42a8-92a1-92748c55583c" alt="splash" width="240"></td>
  </tr>

   <tr>
      <td><img src="https://github.com/user-attachments/assets/90ec438b-f6c6-4f60-8c0f-de59a46ee49a" alt="splash" width="240"></td>
      <td><img src="https://github.com/user-attachments/assets/40bbf319-7b0f-45e6-b95d-145f02294e05" alt="splash" width="240"></td>
      <td><img src="https://github.com/user-attachments/assets/39efd375-0925-470f-8416-f31321a1906e" alt="splash" width="240"></td>
    
  </tr>
</table>

## 🧪 Testing

- UI tests are written using `Compose UI Test`.
- Sample test cases include:
  - Navigating from FAB to Create Task screen
  - Adding tasks
  - Filtering and sorting tasks
  - Completing tasks


## 🗂️ Project Modules

### 1. **App Module**
   - Entry point of the application.
   - Contains UI and app-level configurations.

### 2. **Data Module**
   - Implements repository patterns.

### 3. **Domain Module**
   - Holds business logic and use case classes.
   - Acts as an intermediary between the `Data` module and `App` module.

---

## 📝 Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/AhmedGamalRamadan/TaskManager.git


## Connect with Me 🌐
Let's connect! Feel free to reach out on LinkedIn.
<p align="left">
<a href="https://www.linkedin.com/in/ahmed-gamal-ramadan/" target="blank"><img align="center" src="https://raw.githubusercontent.com/rahuldkjain/github-profile-readme-generator/master/src/images/icons/Social/linked-in-alt.svg" alt="https://www.linkedin.com/in/ahmed-gamal-97509328a/" height="30" width="40" /></a>
</p>
