# 🐾 Catify

Catify is a mobile Android application developed using **Java** and **XML**. The app provides a smooth and modern shopping experience for cat lovers looking for supplies like food, toys, and accessories.

---

## 📱 Features

- 🛍️ **Browse Products Freely**  
  Explore all items without login – add to cart or wishlist as a guest.

- ❤️ **Wishlist**  
  Save your favorite products even without an account.

- 🧺 **Cart**  
  Add products to cart, view quantity and total price.

- 🔐 **Login/Register to Purchase**  
  Buying is restricted to logged-in users. Guests can do everything except checkout.

- 👤 **User Profile**  
  After login, the app displays a profile screen with user details instead of the login button.

- 📧 **Send Email**  
  Contact app support or send product inquiries via email from within the app.

---

## 🛠️ Technologies Used

- Android Studio  
- Java & XML  
- RecyclerView  
- SharedPreferences  
- Email Intents  

---

## 🔐 Authentication Logic

- **Guest users** can browse, add to wishlist/cart, and view product details.  
- Attempting to **Buy Now** triggers a login screen if the user is not authenticated.  
- Once logged in:
  - The **Login/Register** button becomes **Profile**.
  - Tapping it leads to a **Profile screen** displaying the user’s information.  
- The login state is stored using `SharedPreferences`.

---

## 🚀 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/catify.git
   ```

2. Open in **Android Studio** and sync Gradle.  
3. Run on an emulator or device.

---

## ✨ Author

**Shahd Adawi**  
Computer Science Student @ Birzeit University  
Passionate about cats, clean UIs, and Android logic flow.

---

## 📄 License

This project is intended for educational and demonstration purposes only.
