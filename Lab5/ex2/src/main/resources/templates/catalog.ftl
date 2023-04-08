<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Document Catalog</title>
</head>
<body>
    <h1>Document Catalog</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Path/URL</th>
            <th>Tags</th>
        </tr>
        <#list documents as document>
        <tr>
            <td>${document.id}</td>
            <td>${document.title}</td>
            <td>${document.location}</td>
            <td>
                <ul>
                    <#list document.tags?keys as key>
                        <li>${key}: ${document.tags[key]}</li>
                    </#list>
                </ul>
            </td>
        </tr>
        </#list>
    </table>
</body>
</html>