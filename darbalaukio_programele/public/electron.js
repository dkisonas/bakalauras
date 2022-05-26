const electron = require("electron");
const app = electron.app;
const BrowserWindow = electron.BrowserWindow;
const path = require("path");
const isDev = require("electron-is-dev");
const Store = require("electron-store");
const { ipcMain } = require("electron");

let store = new Store();

let mainWindow;

function createWindow() {
  mainWindow = new BrowserWindow({
    width: 900,
    height: 680,
    webPreferences: {
      nodeIntegration: true,
      enableRemoteModule: true,
      contextIsolation: false,
    },
  });
  mainWindow.loadURL(
    isDev
      ? "http://localhost:3000"
      : `file://${path.join(__dirname, "../build/index.html")}`
  );

  mainWindow.on("closed", () => (mainWindow = null));
}

app.on("ready", createWindow);

app.on("window-all-closed", () => {
  if (process.platform !== "darwin") {
    app.quit();
  }
});

app.on("activate", () => {
  if (mainWindow === null) {
    createWindow();
  }
});

ipcMain.handle("create-logbook", async (event, arg) => {
  store.clear();
  console.log("Store cleared.");
  const logbook = {
    identifier: arg[0],
    createdAt: new Date(),
    vesselMasterId: arg[1],
    departureRecord: {},
    catchRecords: [],
    returnToPortRecord: {},
    sent: false,
  };
  store.set("logbook", logbook);
  console.log("Created logbook " + arg);
  const result = await store.get("logbook");
  console.log(result);
  return result;
});

ipcMain.handle("delete-departure", async (event, arg) => {
  store.delete("logbook.departureRecord");
  console.log("departure deleted");
})

ipcMain.handle("update-sent", async (event, arg) => {
  store.set("logbook.sent", true);
  store.set("logbook.departureRecord.sent", true);
  return await store.get("logbook");
})

//Listener for data coming from JSX
ipcMain.on("save-departure", (event, arg) => {
  const departure = JSON.parse(arg);
  store.set("logbook.departureRecord", departure);
  console.log("saved departure in logbook: ");
  console.log(store.get("logbook"));
});

ipcMain.handle('get-logbook', async (event, arg) => {
  const result = await store.get("logbook");
  return result;
});
