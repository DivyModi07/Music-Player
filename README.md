# 🎵 Music Player

A comprehensive console-based music player application built in Java with database integration, user authentication, and playlist management features.

## ✨ Features

### 🎶 Core Music Features
- **Online Song Playback**: Play songs from a predefined database collection
- **Custom Playlist Creation**: Create and manage personal playlists
- **Playlist Management**: Add, remove, and update songs in playlists
- **Music Controls**: Play next, previous, and shuffle functionality
- **Song Information Display**: Shows song name, singer, and duration

### 👤 User Management
- **User Registration**: Sign up with name, mobile, DOB, and password
- **User Authentication**: Secure login system
- **User-specific Playlists**: Each user has their own playlist space

### 🎨 User Experience
- **Colorful Console Interface**: Enhanced visual experience with colored output
- **Interactive Menu System**: Easy navigation through different options
- **Error Handling**: Comprehensive input validation and error management

### 🗄️ Database Integration
- **MySQL Database**: Persistent storage for users, songs, and playlists
- **Stored Procedures**: Optimized database operations
- **Data Validation**: Ensures data integrity and consistency

## 🛠️ Technologies Used

- **Java 8+**
- **MySQL Database**
- **JDBC (Java Database Connectivity)**
- **Java Sound API** (for audio playback)
- **Data Structures**: Custom Deque implementation
- **Collections Framework**: ArrayList, ArrayDeque, HashMap

## 📁 Project Structure

```
Music-Player/
├── MusicPlayer/
│   ├── newmusic1.java          # Main application class
│   └── Colour.java             # Console color management
├── DataStructure/
│   └── Deque1.java             # Custom Deque implementation
└── music_player(database).sql  # Database schema and data
```

## 🔧 Prerequisites

Before running this application, ensure you have:

- **Java Development Kit (JDK) 8 or higher**
- **MySQL Server** (5.7 or higher)
- **MySQL Connector/J** (JDBC driver)
- **Audio files** in WAV format (place in `D://Music/` directory)

## ⚙️ Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/DivyModi07/Music-Player.git
   cd Music-Player
   ```

2. **Set up the database**

3. **Configure audio files**
   - Create a directory: `D://Music/`
   - Place your WAV audio files in this directory
   - Ensure file names match the database entries

4. **Compile and run**
   ```bash
   javac -cp "path/to/mysql-connector.jar" MusicPlayer/*.java DataStructure/*.java
   java -cp ".:path/to/mysql-connector.jar" MusicPlayer.newmusic1
   ```

## 🗄️ Database Setup

1. **Create the database**
   ```sql
   CREATE DATABASE music_player;
   ```

2. **Import the database schema**
   ```bash
   mysql -u root -p music_player < music_player(database).sql
   ```

3. **Verify the tables**
   - `user1`: User account information
   - `songdetails`: Available songs catalog
   - `playlistdetails`: User-specific playlists

## 🚀 Usage

### 1. **Start the Application**
   Run the main class to begin the music player.

### 2. **User Authentication**
   - Choose between Login or Sign-Up
   - For new users: Provide name, mobile number, DOB, and create a password
   - User ID is auto-generated based on name and DOB

### 3. **Main Menu Options**
   - **Play song online**: Access the default song collection
   - **Make your own playlist**: Create a custom playlist
   - **Update your playlist**: Modify existing playlists
   - **Play your playlist song**: Play from your custom playlist
   - **Exit**: Close the application

### 4. **Music Controls**
   - **Next Song**: Play the next song in sequence
   - **Previous Song**: Play the previous song
   - **Shuffle**: Randomize the playlist order
   - **Display Playlist**: View all songs in current playlist

## 🔍 Code Structure

```java
// Main application flow
Main1.main() → Database() → Homepage() → Menu Loop

// User management
User1.CheckUser() → Authentication
User1.SignUp() → Registration with validation

// Playlist operations
PlaylistManager.OnlineSong() → Play online songs
PlaylistManager.MakeYourOwnPlaylist() → Create custom playlist
PlaylistManager.UpdatePlaylist() → Modify existing playlist
```

## 🐛 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure MySQL server is running
   - Check database credentials in the code
   - Verify database exists and is accessible

2. **Audio File Not Found**
   - Ensure audio files are in `D://Music/` directory
   - Check file names match database entries exactly
   - Verify file format is WAV

3. **Compilation Errors**
   - Ensure MySQL Connector/J is in classpath
   - Check Java version compatibility
   - Verify all dependencies are available
