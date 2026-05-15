import TestCase from "./TestCase";


function TestForm(){


    
    return(
    <> 
        <div className="py-8 px-6">
            <h1 className="text-3xl font-bold mt-26 mb-5">Create New Test Suite</h1>
            <form id="test_case">
                <div className="flex  flex-row gap-4 h-10 mb-5">
                    <input type="text" placeholder="Enter suite name" className="w-150 px-3 bg-gray-200 rounded-xl focus:outline-blue-200"/>
                    <input type="text" placeholder="Enter BaseUrl" className="w-100 px-3 bg-gray-200 rounded-xl focus:outline-blue-200"/>
                </div>
                <TestCase/>
            </form>

            {/* <div className="flex gap-4 my-4 py-4">
                <button 
                // onClick={}
                className="text-black p-2 rounded-2xl font-bold border-b-3  hover:text-gray-400 cursor-pointer">
                    Add Test Case
                </button>
                <button 
                // onClick={deleteTest}
                className="text-red-700 p-2 rounded-2xl font-bold border-b-3  hover:text-gray-400 cursor-pointer">
                    Delete Test Case
                </button>
            </div> */}
            
        </div>
    </>
    )
}
export default TestForm;




