const apiUrl = "http://localhost:8080/api/recommendations";

// Функция для отображения продуктов
function displayProducts(containerId, products) {
    const container = document.getElementById(containerId);
    container.innerHTML = ""; // Очистка предыдущих результатов
    if (products.length === 0) {
        container.innerHTML = "<li>No products found.</li>";
        return;
    }
    products.forEach(product => {
        const li = document.createElement("li");
        li.textContent = `${product.name} (Rating: ${product.rating})`;
        container.appendChild(li);
    });
}

// Запрос топовых товаров
document.getElementById("fetchTopRated").addEventListener("click", async () => {
    const response = await fetch(`${apiUrl}/top-rated`);
    const products = await response.json();
    displayProducts("topRatedProducts", products);
});

// Запрос персонализированных рекомендаций
document.getElementById("fetchPersonalized").addEventListener("click", async () => {
    const userId = document.getElementById("userIdInput").value;
    if (!userId) {
        alert("Please enter a User ID.");
        return;
    }
    const response = await fetch(`${apiUrl}/personal/${userId}`);
    const products = await response.json();
    displayProducts("personalRecommendations", products);
});

// Запрос рекомендаций на основе оценок
document.getElementById("fetchBasedOnRatings").addEventListener("click", async () => {
    const userId = document.getElementById("userIdInputRatings").value;
    if (!userId) {
        alert("Please enter a User ID.");
        return;
    }
    const response = await fetch(`${apiUrl}/based-on-ratings/${userId}`);
    const products = await response.json();
    displayProducts("recommendationsBasedOnRatings", products);
});

// Запрос информации о пользователе
document.getElementById("fetchUserDetails").addEventListener("click", async () => {
    const userId = document.getElementById("userIdInputDetails").value;
    if (!userId) {
        alert("Please enter a User ID.");
        return;
    }
    const response = await fetch(`${apiUrl}/user/${userId}`);
    const user = await response.json();
    const userDetails = document.getElementById("userDetails");
    userDetails.innerHTML = `<strong>Name:</strong> ${user.username} <br><strong>Email:</strong> ${user.email}`;
});

// Запрос заказов пользователя
document.getElementById("fetchUserOrders").addEventListener("click", async () => {
    const userId = document.getElementById("userIdInputOrders").value;
    if (!userId) {
        alert("Please enter a User ID.");
        return;
    }
    const response = await fetch(`${apiUrl}/user/${userId}/orders`);
    const orders = await response.json();
    const userOrders = document.getElementById("userOrders");
    userOrders.innerHTML = "";
    if (orders.length === 0) {
        userOrders.innerHTML = "<li>No orders found.</li>";
        return;
    }
    orders.forEach(order => {
        const li = document.createElement("li");
        li.textContent = `Order ID: ${order.orderId}, Date: ${order.date}`;
        userOrders.appendChild(li);
    });
});

// Запрос оценок пользователя
document.getElementById("fetchUserRatings").addEventListener("click", async () => {
    const userId = document.getElementById("userIdInputRatingsDetails").value;
    if (!userId) {
        alert("Please enter a User ID.");
        return;
    }
    const response = await fetch(`${apiUrl}/user/${userId}/ratings`);
    const ratings = await response.json();
    const userRatings = document.getElementById("userRatings");
    userRatings.innerHTML = "";
    if (ratings.length === 0) {
        userRatings.innerHTML = "<li>No ratings found.</li>";
        return;
    }
    ratings.forEach(rating => {
        const li = document.createElement("li");
        li.textContent = `${rating.product.name} - Rating: ${rating.rating}`;
        userRatings.appendChild(li);
    });
});
