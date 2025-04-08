import React, { useEffect } from "react";
import usePanelResizer from "../../hooks/UsePanelResizer"; // Import the custom hook
import "./Home.css";
import Devises from "../devise/Devise";
import PositionPannel from "../positionPannel/PositionPanel";
import AddOperation from "../operation/AddOperation";
import { color } from "chart.js/helpers";


function Home() {




  // Using the custom hook for vertical resizing of the panels
  const { panelSize: topPanelHeight, onMouseDown: onTopPanelMouseDown, setPanelSize } = usePanelResizer(window.innerHeight / 2, "vertical");

  // Calculate bottom panel height based on the top panel height
  const bottomPanelHeight = window.innerHeight - topPanelHeight - 3; // Subtract the splitter height

  // Handle window resize to update panel sizes dynamically
  useEffect(() => {
    const handleResize = () => {
      const newTopPanelHeight = window.innerHeight / 2;
      const newBottomPanelHeight = window.innerHeight - newTopPanelHeight - 3; // Adjust for splitter height
      setPanelSize(newTopPanelHeight); // Update top panel size when window resizes
    };

    window.addEventListener("resize", handleResize);
    return () => window.removeEventListener("resize", handleResize);
  }, [setPanelSize]);

   return (
    // <div className="blackbackground">
    // <AddOperation />
    // </div>
    <div className="Home">
      {/* Top Panel */}
      <div className="top-panel" style={{ height: `${topPanelHeight}px` }}>
        <Devises />
      </div>

      {/* Resizable Splitter */}
      <div
        className="home-splitter"
        onMouseDown={onTopPanelMouseDown} // Trigger the resizing logic when the user clicks on the splitter
        style={{
          cursor: "ns-resize", 
          position: "absolute", // This will allow it to slide freely
          top: `${topPanelHeight}px`, // Make sure it stays just below the top panel
          width: "100%", // Ensure it spans the full width of the container
          height: "3px", // Adjust as needed
          backgroundColor: "#b9893c", 
          zIndex: 1000, // Make sure it's on top of both panels and workspace
        }}
      />

      {/* Bottom Panel */}
      <div className="bottom-panel" style={{ height: `${bottomPanelHeight}px` }}>
        <PositionPannel />
      </div>
    </div>
  );
}

export default Home;



// import React from "react";
// import usePanelResizer from "../../hooks/UsePanelResizer"; // Import the custom hook

// import "./Home.css"
// // Import CSS file
// import Devises from "../devise/Devise";
// import PositionPannel from "../positionPannel/PositionPanel";
// import DeviseListStream from "../devise/deviseList/DeviseListStream";
// import AddOperation from "../operation/AddOperation";

// function Home() {
// /**      <h1>Welcome to BFITrading</h1>
//       <p>Where Knowledge Shapes Civilizations. Explore Your Destiny.</p> */
// /*      <Devises />  */
//   // Use the hook for the vertical resizer (top/bottom panel)
//   const { panelSize: topPanelHeight, onMouseDown: onTopPanelMouseDown } = usePanelResizer(window.innerHeight / 2, "vertical");

//   // Calculate the bottom panel height based on the top panel height
//   const bottomPanelHeight = window.innerHeight - topPanelHeight - 3;

//   return (

//     // <div>
//     //   <AddOperation />
//     //   </div>

//     <div className="Home"style={{ display: "flex", flexDirection: "column", height: "100vh" }}>
//     {/* Top Panel */}
//     <div
//       className="top-panel"
//       style={{ height: `${topPanelHeight}px` }}
//     >
//       <Devises />
//       </div>

//     {/* Resizable Splitter (Vertical) */}
//     <div
//       className="home-splitter"
//       onMouseDown={onTopPanelMouseDown}
//     />

//     {/* Bottom Panel */}
//     <div
//       className="bottom-panel"
//       style={{
//         flexGrow: 1,
//         height: `${bottomPanelHeight}px`, // Dynamically adjust the height of the bottom panel
//       }}
//     >
//       <PositionPannel />
//       </div>
//     </div>


// );
// }

// export default Home;
