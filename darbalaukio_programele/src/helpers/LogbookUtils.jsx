import { v4 as uuidv4 } from "uuid";
import http from "../services/http-service";

const getPositionType = (positionType) => {
  if (positionType === "Coordinates") {
    return "POSITION";
  } else {
    return "LOCATION";
  }
};

const getLogbook = async (ipcRenderer) => {
  return await ipcRenderer.invoke("get-logbook");
};

const createLogbook = async (ipcRenderer, id) => {
  return await ipcRenderer.invoke("create-logbook", [uuidv4(), id]);
};

const deleteDeparture = async (ipcRenderer) => {
  return await ipcRenderer.invoke("delete-departure");
};

const updateRecords = async (ipcRenderer) => {
  return await ipcRenderer.invoke("update-sent");
}

const sendLogbook = async (ipcRenderer, logbook, setLogbook) => {
  return http
    .post("/logbooks/save", logbook)
    .then((response) => {
      if (response.status === 200) {
        console.log(response.data.message);
        window.alert(response.data.message);
        const lb = {...logbook};
        lb.sent = true;
        updateRecords(ipcRenderer);
        setLogbook(lb);
      }
    })
    .catch((error) => {
      const resMessage = error.response.data
        ? error.response.data
        : error.message;
      console.log(resMessage);
    });
};

const LogbookUtils = {
  getPositionType,
  getLogbook,
  createLogbook,
  deleteDeparture,
  sendLogbook,
  updateRecords
};

export default LogbookUtils;
