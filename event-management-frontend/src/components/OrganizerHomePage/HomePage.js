import React, { useState } from 'react';
import styles from './HomePage.module.css';
import BookEvent from '../BookEvents/BookEvent';
import BookVendor from '../BOOKVENDORS/BookVendor';
import BookDesigner from '../BOOKDESIGNER/BookDesigner'; // Import BookDesigner
import EditUserForm from '../MANAGEUSER/EditUserForm'; // Import EditUserForm
import { useNavigate } from 'react-router-dom'; // Import useNavigate

function HomePage({ showBookEvent, showBookVendor, showBookDesigner, showEditUserForm, onBack, onEditUserBack }) {
  const [tasks, setTasks] = useState([]);
  const [newTask, setNewTask] = useState('');
  const navigate = useNavigate(); // Initialize useNavigate

  const handleAddTask = () => {
    if (newTask.trim()) {
      setTasks([...tasks, { text: newTask, completed: false }]);
      setNewTask('');
    }
  };

  const handleInputChange = (event) => {
    setNewTask(event.target.value);
  };

  const handleToggleComplete = (index) => {
    const updatedTasks = tasks.map((task, i) =>
      i === index ? { ...task, completed: !task.completed } : task
    );
    setTasks(updatedTasks);
  };

  const handleRemoveTask = (index) => {
    const updatedTasks = tasks.filter((_, i) => i !== index);
    setTasks(updatedTasks);
  };

  const handleLogout = () => {
    // Add any necessary logout logic here (e.g., clearing local storage, session)
    navigate('/'); // Navigate to the EventHomePage
  };

  return (
    <div className={styles.homePage}>
      <h1 className={styles.heading}>Welcome to Organizer HomePage</h1>
      {!showBookEvent && !showBookVendor && !showBookDesigner && !showEditUserForm ? (
        <div className={styles.todoContainer}>
          <h2>To-Do List</h2>
          <div className={styles.addTask}>
            <input
              type="text"
              value={newTask}
              onChange={handleInputChange}
              placeholder="Add new task"
            />
            <button onClick={handleAddTask}>Add</button>
          </div>
          {tasks.length > 0 ? (
            <ul className={styles.taskList}>
              {tasks.map((task, index) => (
                <li
                  key={index}
                  className={`${styles.taskItem} ${
                    task.completed ? styles.completed : ''
                  }`}
                >
                  <input
                    type="checkbox"
                    checked={task.completed}
                    onChange={() => handleToggleComplete(index)}
                  />
                  <span>{task.text}</span>
                  <button
                    className={styles.removeButton}
                    onClick={() => handleRemoveTask(index)}
                  >
                    X
                  </button>
                </li>
              ))}
            </ul>
          ) : (
            <p>No tasks yet!</p>
          )}
        </div>
      ) : showBookEvent ? (
        <BookEvent onBack={onBack} />
      ) : showBookVendor ? (
        <BookVendor onBack={onBack} />
      ) : showBookDesigner ? (
        <BookDesigner onBack={onBack} />
      ) : showEditUserForm ? (
        <EditUserForm onBack={onEditUserBack} />
      ) : null}
      {/* Logout button outside the conditional content */}
      {!showBookEvent && !showBookVendor && !showBookDesigner && !showEditUserForm && (
        <button className={styles.logoutButton} onClick={handleLogout}>
          Logout
        </button>
      )}
    </div>
  );
}

export default HomePage;