<%@ page import="com.academy.course.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>

    table {
        width: 50%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    th, td {
        border: 1px solid black;
        padding: 8px;
        text-align: left;
    }

    th {
        background-color: #f2f2f2;
    }

    /* Стилизуем форму как сетку */
    .my-form {
        display: grid;
        grid-template-columns: 120px 300px; /* 1-я колонка для текста, 2-я для полей */
        gap: 15px; /* Расстояние между строками и столбцами */
        align-items: center; /* Центрируем текст по вертикали относительно инпута */
    }

    .my-form input {
        padding: 5px;
        width: 100%; /* Заполнит свои 300px из сетки */
    }

    .my-form button {
        grid-column: 1 / span 2; /* Кнопка растянется на обе колонки */
        width: fit-content;
    }
</style>
<html>
<head>
    <title>Product List</title>
    <h1>Products</h1>
        <a href="index.jsp">Вернуться на главную страницу</a>
        <br>
        <br>
        <form method="post" action="editPage.jsp"
              style="display: flex; flex-direction: column; gap: 10px; max-width: 300px;">
            <div><input type="submit" value="Добавить запись" style="width: 100%;"></div>
        </form>
    </head>
<body>
<table>
    <thead>
    <tr>
        <th>Product Number</th>
        <th>Name</th>
        <th>Price</th>
        <th>Info</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <%
        // Получаем список из request, который положил туда Сервлет
        List<ProductDTO> productDTOList = (List<ProductDTO>) request.getAttribute("products");

        // Проверяем, что список не null, чтобы не было ошибки
        if (productDTOList != null && !productDTOList.isEmpty()) {
            for (ProductDTO productDTO : productDTOList) {
    %>
    <tr>
        <td><%= productDTO.getId() %>
        </td>
        <td><%= productDTO.getName() %>
        </td>
        <td><%= productDTO.getPrice() %>
        </td>
        <td><%= productDTO.getInfo() %>
        </td>
        <td><%= productDTO.getManufacturer() %>
        </td>
        <td><%= productDTO.getBestBefore() %>
        </td>
        <td>
            <form method="post" action="findCar" style="display: flex; flex-direction: column; gap: 10px;">
                <div><input type="submit" value="Редактировать запись" style="width: 100%;"></div>
                <input type="hidden" name="id" value="<%=productDTO.getId()%>">
                <input type="hidden" name="action" value="editPage">
            </form>
        </td>
        <td>
            <form method="post" action="removeCar" style="display: flex; flex-direction: column; gap: 10px;">
                <div><input type="submit" value="Удалить запись" style="width: 100%;"></div>
                <input type="hidden" name="id" value="<%=productDTO.getId()%>">
            </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="2" style="text-align: center;">Данных нет. Нажмите "Посмотреть базу".</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>

</body>
</html>
