<!DOCTYPE html>

<html lang="en">

<body>
	<table>
		<thead>
			<tr>
				<td>Title</td>
				<td>Body</td>
				<td>Tags</td>
			</tr>
		</thead>
		<tbody>
<#list notas as note>
			<tr>
				<td>${note.titulo}</td>
				<td>${note.contenido}</td>
				<td>
<#list note.etiquetas as tag>
					<span>${tag.nombre}</span>
</#list>
				</td>
			</tr>
</#list>
		</tbody>
	</table>
</body>

</html>