import React from "react";
import LogbookUtils from "../helpers/LogbookUtils";

function SendLogbookButton({ipcRenderer, logbook, setLogbook}) {
  return (
    <button
      type="button"
      className="inline-flex items-center px-2.5 py-1.5 border border-transparent text-xs font-medium rounded shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
        onClick={async () => LogbookUtils.sendLogbook(ipcRenderer, logbook, setLogbook)}
    >
      Send Logbook
    </button>
  );
}

export default SendLogbookButton;
