for $x in doc("bin//XML_TASK.xml")/catalog/booklist/book
where $x/price>5.95
return $x/title