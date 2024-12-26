const apiUrl = "http://localhost:8080/api/recommendations";
async function fetchData(url) {
    try {
        const response = await fetch(url);
        const data = await response.json();  // Проверка на валидность JSON
        return data;
    } catch (error) {
        console.error("Ошибка при обработке данных:", error);
        alert("Ошибка при получении данных.");
        return null;
    }
}

document.getElementById("fetchUserDetails").addEventListener("click", async () => {
    const userId = document.getElementById("userIdInputDetails").value;
    if (!userId) {
        alert("Please enter a User ID.");
        return;
    }
    const data = await fetchData(`${apiUrl}/user/${userId}`);
    if (data) {
        displayUserDetails(data);
    }
});

// Fetch User Details
document.getElementById("fetchUserDetails").addEventListener("click", async () => {
    const userId = document.getElementById("userIdInputDetails").value;
    if (!userId) {
        alert("Please enter a User ID.");
        return;
    }
    const response = await fetch(`${apiUrl}/user/${userId}`);
    const user = await response.json();
    displayUserDetails(user);
});

// Fetch Top-Rated Products
document.getElementById("fetchTopRated").addEventListener("click", async () => {
    const response = await fetch(`${apiUrl}/top-rated`);
    const products = await response.json();
    displayProducts("topRatedProducts", products);
});

// Fetch Personalized Recommendations
document.getElementById("fetchPersonalized").addEventListener("click", async () => {
    const userId = document.getElementById("userIdInputPersonal").value;
    if (!userId) {
        alert("Please enter a User ID.");
        return;
    }
    const response = await fetch(`${apiUrl}/personal/${userId}`);
    const products = await response.json();
    displayProducts("personalRecommendations", products);
});

// Fetch Recommendations Based on Ratings
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

// Display User Details
function displayUserDetails(user) {
    const userDetailsDiv = document.getElementById("userDetails");
    userDetailsDiv.innerHTML = `
        <p><strong>Username:</strong> ${user.username}</p>
        <p><strong>Email:</strong> ${user.email}</p>
        <p><strong>Created At:</strong> ${new Date(user.createdAt).toLocaleString()}</p>
    `;
}

// Display Orders
function displayOrders(orders) {
    const ordersList = document.getElementById("userOrders");
    ordersList.innerHTML = orders.map(order => `
        <li>Order ID: ${order.orderId} - Date: ${new Date(order.orderDate).toLocaleString()}</li>
    `).join('');
}

// Display Products
function displayProducts(elementId, products) {
    const list = document.getElementById(elementId);
    list.innerHTML = products.map(product => `
        <li>
            <strong>${product.name}</strong><br>
            Category: ${product.category}<br>
            Price: $${product.price}<br>
            Rating: ${product.rating ? product.rating : "No rating"}
        </li>
    `).join('');
}
