
# ⚓ Battleship

A **classic Battleship** game built in **Java** with a **JavaFX** graphical interface.  

---

## 🎮 Features

- Two-player local gameplay  
- Manual ship placement on each player’s grid  
- Turn-based play with hit and miss detection  
- Simple and clear JavaFX interface  
- Game-over detection when one player’s ships are all destroyed  

---

## 🛠️ Getting Started

### Prerequisites

- **Java Development Kit (JDK)** — version 11 or newer  
- **JavaFX SDK** — required if your JDK doesn’t include JavaFX  
- (Optional) IDE like IntelliJ IDEA or Eclipse for easier setup  

## 🚀 How to Run (Eclipse)

1. Clone the repository  
   git clone https://github.com/Loona6/Battleship.git

2. Open Eclipse  
   Go to **File → Import → Existing Projects into Workspace**  
   Select the **Battleship** folder  
   Click **Finish**

3. Set up JavaFX  
   - Go to **Run → Run Configurations...**  
   - Under **VM arguments**, add the following line:  
     --module-path "C:\path\to\javafx\lib" --add-modules javafx.controls,javafx.fxml  
     (Replace the path with your JavaFX SDK location)

4. Run the game  
   - Right-click on the main file (for example, `Main.java`)  
   - Select **Run As → Java Application**

---

## 🧩 How to Play

1. Each player places their ships on their grid.  
2. Players take turns choosing coordinates to attack.  
3. The board shows hits and misses.  
4. The first player to destroy all enemy ships wins.

---

## 📂 Project Structure

Battleship/  
├── src/ — game source files  
│   ├── model/ — game logic (ships, board, rules)  
│   ├── view/ — JavaFX UI  
│   └── controller/ — event handling  
├── resources/ — FXML, images, and CSS  
└── README.md

---

## ⚠️ Known Limitations

- Two-player local only (no AI or online play)  
- Basic UI (no animations or sounds)  
- No save/load feature  

---

## 💡 Future Plans

- Add single-player mode with AI  
- Add sound and animations  
- Improve visuals and ship placement UX  
- Add online multiplayer  

---

## 🪪 License

This project is for **academic purposes only** and is not intended for commercial use.  
All source code and materials are shared under the terms of this repository.
