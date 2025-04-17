
/*
export const fetchForexStream = (setDeviseData) => {  
    const eventSource = new EventSource('http://localhost:8084/marcheDeviseEyaYassmine/HDevises/api/forex-stream');  
  
    eventSource.onmessage = (event) => {
      const newData = JSON.parse(event.data);
      console.log('Received data:', newData);
  
      // Update state by only adding new data based on unique identifiers (e.g., libelle, symbol)
      setDeviseData(prevData => {
        // Remove old data with the same libelle and symbol
        const updatedData = prevData.filter(item => 
          !newData.some(newItem => newItem.slibelle === item.slibelle && newItem.ssymbol === item.ssymbol)
        );
  
        // Add new data
        return [...updatedData, ...newData];
      });
    };
  
    eventSource.onerror = (error) => {
      console.error('Error with EventSource:', error);
    };
  
    return eventSource;
  };
*/


  export const fetchForexStream = (setDeviseData) => {  
    const eventSource = new EventSource('http://localhost:8084/marcheDeviseEyaYassmine/HRates/api/forex-stream');  

    eventSource.onmessage = (event) => {
        const newData = JSON.parse(event.data);
        console.log('Received data:', newData);

        // Get previous data from localStorage
        const prevData = JSON.parse(localStorage.getItem('deviseData')) || [];

        // Process new data and compare with previous data
        const processedData = newData.map(newItem => {
            const prevItem = prevData.find(item => 
                item.label === newItem.label && item.ssymbol === newItem.ssymbol
            );

            return {
                ...newItem,
                colorBorrow: prevItem ? (newItem.sborrow > prevItem.sborrow ? '#29e61c' : newItem.sborrow < prevItem.sborrow ? 'red' : 'white') : 'white',
                colorLend: prevItem ? (newItem.slend > prevItem.slend ? '#29e61c' : newItem.slend < prevItem.slend ? 'red' : 'white') : 'white',
                colorAvg: prevItem ? (newItem.sintrestaverage > prevItem.sintrestaverage ? '#29e61c' : newItem.sintrestaverage < prevItem.sintrestaverage ? 'red' : 'white') : 'white',
                colorSpread: prevItem ? (newItem.sintrestspread > prevItem.sintrestspread ? '#29e61c' : newItem.sintrestspread < prevItem.sintrestspread ? 'red' : 'white') : 'white',
            };
        });

        // Store the updated data in localStorage for next comparison
        localStorage.setItem('deviseData', JSON.stringify(newData));

        // Update the state with the new processed data
        setDeviseData(processedData);
    };

    eventSource.onerror = (error) => {
        console.error('Error with EventSource:', error);
    };

    return eventSource;
};


  ///////to store locally and compare later


  /*

  export const fetchForexStream = (setDeviseData) => {  
    const eventSource = new EventSource('http://localhost:8084/marcheDeviseEyaYassmine/HDevises/api/forex-stream');  

    eventSource.onmessage = (event) => {
        const newData = JSON.parse(event.data);
        console.log('Received data:', newData);

        // Get previous data from localStorage
        const prevData = JSON.parse(localStorage.getItem('deviseData')) || [];

        // Process new data with color changes
        const processedData = newData.map(newItem => {
            const prevItem = prevData.find(item => 
                item.slibelle === newItem.slibelle && item.ssymbol === newItem.ssymbol
            );

            return {
                ...newItem,
                colorBorrow: prevItem ? (newItem.sborrow > prevItem.sborrow ? 'green' : 'red') : 'black',
                colorLend: prevItem ? (newItem.slend > prevItem.slend ? 'green' : 'red') : 'black',
                colorAvg: prevItem ? (newItem.sintrestaverage > prevItem.sintrestaverage ? 'green' : 'red') : 'black',
                colorSpread: prevItem ? (newItem.sintrestspread > prevItem.sintrestspread ? 'green' : 'red') : 'black',
            };
        });

        // Store new data in localStorage for next update
        localStorage.setItem('deviseData', JSON.stringify(newData));

        // Update the state with the new colored data
        setDeviseData(processedData);
    };

    eventSource.onerror = (error) => {
        console.error('Error with EventSource:', error);
    };

    return eventSource;
};




  */
  

  ///////////////////////////////////////////
// import { useState, useEffect } from "react";

// const useForexStream = () => {
//   const [forexData, setForexData] = useState([]); // Ensure it's always an array

//   useEffect(() => {
//     const eventSource = new EventSource("http://localhost:8084/marcheDeviseEyaYassmine/HDevises/api/forex-stream");

//     eventSource.onmessage = (event) => {
//       const newData = JSON.parse(event.data);
//       console.log("Received new data:", newData); // Debugging log

//       setForexData((prevData) => {
//         if (!Array.isArray(prevData)) prevData = []; // Ensure prevData is always an array

//         const existingIndex = prevData.findIndex(
//           (item) => item.slibelle === newData.slibelle && item.ssymbol === newData.ssymbol
//         );

//         if (existingIndex !== -1) {
//           // Update the existing DeviseH
//           const updatedData = [...prevData];
//           updatedData[existingIndex] = newData;
//           return updatedData;
//         } else {
//           // Add the new DeviseH if it doesn't exist
//           return [...prevData, newData];
//         }
//       });
//     };

//     eventSource.onerror = (error) => {
//       console.error("Error in SSE stream:", error);
//       eventSource.close();
//     };

//     return () => {
//       eventSource.close(); // Cleanup when component unmounts
//     };
//   }, []);

//   return forexData;
// };

// export default useForexStream;
















// import { useState, useEffect } from 'react';

// // Service to consume SSE stream
// const useForexStream = () => {
//   const [forexData, setForexData] = useState(null);

//   useEffect(() => {
//     const eventSource = new EventSource('http://localhost:8084/marcheDeviseEyaYassmine/HDevises/api/forex-stream');

//     eventSource.onmessage = function (event) {
//       const newData = JSON.parse(event.data);
//       setForexData(newData); // Update the state with new forex data


//     //   setForexData((prevData = []) => {
//     //     // Ensure prevData is always an array (default to [])
//     //     if (!Array.isArray(prevData)) prevData = [];

//     //     // Find if the DeviseH already exists in the list
//     //     const existingIndex = prevData.findIndex(
//     //       (item) => item.slibelle === newData.slibelle && item.ssymbol === newData.ssymbol
//     //     );

//     //     if (existingIndex !== -1) {
//     //       // Update the existing DeviseH
//     //       const updatedData = [...prevData];
//     //       updatedData[existingIndex] = newData;
//     //       return updatedData;
//     //     } else {
//     //       // Add the new DeviseH if it doesn't exist
//     //       return [...prevData, newData];
//     //     }
//     //   });
//     };

//     eventSource.onerror = function (error) {
//       console.error('Error in SSE stream:', error);
//       eventSource.close(); // Close the connection if there's an error
//     };

//     return () => {
//       eventSource.close(); // Clean up when component unmounts
//     };
//   }, []);

//   return forexData;
// };

// export default useForexStream;






// SseService.js
// export const createSseConnection = (onMessage, onError) => {
//     // Create a new EventSource to listen for SSE from backend
//     const eventSource = new EventSource("http://localhost:8084/marcheDeviseEyaYassmine/HDevises/stream-devises");

//     // When new data is received, invoke the onMessage callback
//     eventSource.onmessage = function (event) {
//         try {
//             const newDeviseH = JSON.parse(event.data); // Parse the incoming data
//             onMessage(newDeviseH); // Pass it to the callback
//         } catch (error) {
//             console.error("Error parsing SSE data:", error);
//         }
//     };

//     // Handle errors by invoking the onError callback
//     eventSource.onerror = function (error) {
//         console.error("Error with SSE connection:", error);
//         onError(error); // Pass the error to the callback
//         eventSource.close(); // Close the connection if an error occurs
//     };

//     // Return the EventSource so it can be closed or reused later
//     return eventSource;
// };
