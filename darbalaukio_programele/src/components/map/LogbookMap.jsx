import React, { useState, useEffect } from "react";
import { Map, Marker, Overlay } from "pigeon-maps";
import { useNavigate } from "react-router-dom";

function LogbookMap({logbook}) {
  const navigate = useNavigate();
  const [currentPayload, setCurrentPayload] = useState();
  const [currentAnchor, setCurrentAnchor] = useState([]);
  const [showOverlay, setShowOverlay] = useState(false);

  const handleMouseOver = (anchor, payload) => {
    setCurrentPayload(payload);
    setShowOverlay(true);
    setCurrentAnchor(anchor);
  };

  const handleMouseOut = () => {
    setShowOverlay(false);
    setCurrentAnchor([]);
  };

  const openDeparture = () => {
    navigate("/departure", { state: logbook });
  };

  useEffect(() => {
    console.log("logbook changed");
  }, [logbook])
  

  return (
    <Map height={300} defaultCenter={[65, 2.5]} defaultZoom={3}>
      {(logbook !== undefined && logbook?.departureRecord?.position) && (
        <Marker
          payload={{ label: "Departure", dateTime: new Date(logbook.departureRecord.dateTime).toLocaleString() }}
          onMouseOver={({ event, anchor, payload }) => {
            handleMouseOver(anchor, payload);
          }}
          onMouseOut={({ event, anchor, payload }) => {
            handleMouseOut();
          }}
          onClick={openDeparture}
          width={50}
          color={logbook.sent === true ? "hsl(93, 100%, 50%)" : "hsl(40, 2%, 50%)"}
          anchor={logbook?.departure?.position?.value}
        />
      )}

      {showOverlay && (
        <Overlay anchor={currentAnchor} offset={[100, 140]}>
          <div className="bg-white shadow overflow-hidden sm:rounded-lg">
            <div className="px-4 py-5 sm:px-6">
              <h3 className="text-lg leading-6 font-medium text-gray-900">
                {currentPayload.label}
              </h3>
              <p className="mt-1 max-w-xs text-xs text-gray-500">
                {currentPayload.dateTime}
              </p>
            </div>
          </div>
        </Overlay>
      )}
    </Map>
  );
}

export default LogbookMap;
