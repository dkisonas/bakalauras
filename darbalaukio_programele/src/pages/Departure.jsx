import React, { useState } from "react";

import DatePickerField from "../components/input/fields/DatePickerField";
import DepartureReasonDropdown from "../components/input/dropdowns/DepartureReasonDropdown";
import FisheryTypeDropdown from "../components/input/dropdowns/FisheryTypeDropdown";
import PositionTypeField from "../components/input/fields/PositionTypeField";
import TargetSpeciesGroupDropdown from "../components/input/dropdowns/TargetSpeciesGroupDropdown";
import PortsDropdown from "../components/input/dropdowns/PortsDropdown";
import CoordinatesField from "../components/input/fields/CoordinatesField";
import BackButton from "../components/BackButton";
import GearTable from "../components/input/tables/GearTable";
import CatchTable from "../components/input/tables/CatchTable";

import { useRef, useEffect } from "react";
import { useFieldArray, useForm } from "react-hook-form";
import { v4 as uuid } from "uuid";

import LogbookUtils from "../helpers/LogbookUtils";
import { useLocation, useNavigate } from "react-router-dom";

function Departure({ ipcRenderer }) {
  const { state } = useLocation();
  const navigate = useNavigate();
  const isInitialMount = useRef(true);
  const [dateTime, setDateTime] = useState();
  const [positionType, setPositionType] = useState();

  const getDefaultValues = () => {
    if (!state) {
      return {
        defaultValues: {
          identifier: uuid(),
          positionType: "Coordinates",
          coordinates: "60, 3",
          fisheryType: "NWCOD",
          departureReason: "GUD",
          targetSpeciesGroup: "SCALLOP",
          gear: [{identifier: uuid(), gearTypeCode: "SB" }],
          catches: [
            {
              identifier: uuid(),
              species: "BXD",
              count: "1",
              weight: "50",
              presentation: "SAL",
              preservation: "SMO",
            },
          ],
        },
      };
    } else {
      let position = loadPosition();
      return {
        defaultValues: {
          identifier: state.departureRecord.identifier,
          ...position,
          fisheryType: state.departureRecord.fisheryType,
          departureReason: state.departureRecord.departureReason,
          targetSpeciesGroup: state.departureRecord.targetSpeciesGroup,
          gear: [...state.departureRecord.gearOnBoard],
          catches: [...state.departureRecord.catchOnBoard],
        },
      };
    }
  };

  const handleSave = (data) => {
    const posType = LogbookUtils.getPositionType(positionType);
    const departureRecord = {
      identifier: data.identifier,
      dateTime: dateTime,
      position: {
        type: posType,
        value: posType === "POSITION" ? data.coordinatesResult.toString() : data.port,
      },
      departureReason: data.departureReason,
      fisheryType: data.fisheryType,
      targetSpeciesGroup: data.targetSpeciesGroup,
      gearOnBoard: [...data.gear],
      catchOnBoard: [...data.catches],
      sent: false,
    };
    ipcRenderer.send("save-departure", JSON.stringify(departureRecord));
    console.log(departureRecord);
    navigate("/dashboard");
    window.location.reload();
  };

  const formControl = useForm(getDefaultValues());

  const { control, register, unregister, setValue, handleSubmit } = formControl;

  const gearTableControl = useFieldArray({
    control,
    name: "gear",
  });

  const catchTableControl = useFieldArray({
    control,
    name: "catches",
  });

  const deleteDeparture = async () => {
    const dialog = window.confirm("Delete departure?");
    if (dialog) {
      await LogbookUtils.deleteDeparture(ipcRenderer);
      navigate("/dashboard");
      window.location.reload();
    }
  };

  useEffect(() => {
    if (state) {
      const position = loadPosition();
      setPositionType(position.positionType);
      setDateTime(new Date(state.departureRecord.dateTime));
    } else {
      setPositionType("Coordinates");
      setDateTime(new Date());
    }
  }, [state]);

  useEffect(() => {
    if (isInitialMount.current) {
      isInitialMount.current = false;
    } else {
      if (positionType === "Coordinates") {
        register("coordinates");
        unregister("port");
      } else if (positionType === "Port") {
        register("port");
        unregister("coordinates");
        setValue("port", "");
      }
    }
  }, [positionType]);

  return (
    <div className="p-8">
      <form
        className="space-y-8 divide-y divide-gray-200"
        onSubmit={handleSubmit(handleSave)}
      >
        <div className="space-y-8 divide-y divide-gray-200">
          <div>
            <div className="grid grid-cols-2">
              <h3 className="text-lg leading-6 font-medium text-gray-900">
                Departure information
              </h3>
              <div className="mx-auto">
                <BackButton />
              </div>
            </div>

            <div className="mt-6 grid grid-cols-1 gap-y-6 gap-x-4 sm:grid-cols-6">
              <div className="sm:col-span-4">
                <DatePickerField
                  label="Departure date"
                  value={dateTime}
                  setParent={setDateTime}
                />
              </div>

              <div className="sm:col-span-4">
                <PositionTypeField
                  formControl={formControl}
                  setParent={setPositionType}
                />
                {positionType === "Port" ? (
                  <PortsDropdown
                    formControl={formControl}
                    registerName="port"
                  />
                ) : (
                  <>
                    <CoordinatesField
                      registerName={"coordinates"}
                      formControl={formControl}
                    />
                  </>
                )}
              </div>

              <div className="sm:col-span-6">
                <DepartureReasonDropdown
                  formControl={formControl}
                  registerName="departureReason"
                />
              </div>

              <div className="sm:col-span-6">
                <FisheryTypeDropdown
                  formControl={formControl}
                  registerName="fisheryType"
                />
              </div>

              <div className="sm:col-span-6">
                <TargetSpeciesGroupDropdown
                  formControl={formControl}
                  registerName="targetSpeciesGroup"
                />
              </div>

              <div className="sm:col-span-6">
                <GearTable
                  formControl={formControl}
                  tableControl={gearTableControl}
                  registerName="gear"
                />
              </div>
              <div className="sm:col-span-6">
                <CatchTable
                  formControl={formControl}
                  tableControl={catchTableControl}
                  registerName="catches"
                />
              </div>
            </div>
          </div>
        </div>

        <div className="pt-5">
          <div className="flex justify-end">
            {state && (
              <button
              type="button"
              onClick={deleteDeparture}
              className="bg-red-300 py-2 px-4 mr-3 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 hover:bg-red-200 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              Delete
            </button>
            )}
            <button
              type="button"
              onClick={() => navigate('/dashboard')}
              className="bg-white py-2 px-4 border border-gray-300 rounded-md shadow-sm text-sm font-medium text-gray-700 hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              Cancel
            </button>
            <button
              type="submit"
              className="ml-3 inline-flex justify-center py-2 px-4 border border-transparent shadow-sm text-sm font-medium rounded-md text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"
            >
              Save
            </button>
          </div>
        </div>
      </form>
    </div>
  );

  function loadPosition() {
    let position;
    if (state.departureRecord.position.type === "POSITION") {
      position = {
        positionType: "Coordinates",
        coordinates: state.departureRecord.position.value.toString(),
      };
    } else {
      position = {
        positionType: "Port",
        port: state.departureRecord.position.value,
      };
    }
    return position;
  }
}

export default Departure;
