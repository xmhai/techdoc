## Fixed html table width
width:xxxpx or width:max-width is not working, should set to max-content.
```css
table {
	margin: 0 auto;
	table-layout:fixed;
	width: max-content;
}
table th {
	word-break:break-word;
}
td:nth-child(1) {  
	word-break:break-all;
	width: 200px;
}	  
td:nth-child(2) {  
	word-break:break-all;
	width: 120px;
}
```
