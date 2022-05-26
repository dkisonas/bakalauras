import { v4 as uuidv4 } from "uuid";

const getCopiedRowsAndFindRow = (listToCopy, id) => {
  const copiedRows = [...listToCopy];
  const foundRow = copiedRows.find((row) => row.id === id);
  return { copiedRows, foundRow };
};

const deleteRow = (rows, setRows, id) => {
  const newList = rows.filter((row) => {
    return row.id !== id;
  });
  setRows(newList);
};

const addRow = (rows, setRows) => {
  const uuid = uuidv4();
  const newList = rows.concat({
    id: uuid,
  });
  setRows(newList);
};

const TableUtils = {
  getCopiedRowsAndFindRow,
  deleteRow,
  addRow,
};

export default TableUtils;
