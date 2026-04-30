
const addAssertion=()=> {

}


function TestCase(){
    return(
        <>
            <div className=" px-8 py-6 rounded-xl">

                <span className="font-bold">Test name: </span>
                <input type="text" name="name" className="block w-100 mb-6 mt-2 focus:outline-blue-300 bg-gray-200 px-4 py-2 rounded-xl" placeholder="Enter Test name"/>

                <span className="font-bold">Method: </span>
                <select name="method" className="mr-8 border-2 text-blue-400 font-bold focus:outline-blue-200 border-1 w-50 py-2 rounded-2xl px-4">
                    <option value="placeholder" className="text-gray-400">Enter method</option>
                    <option value="GET" className="text-green-400 font-bold">GET</option>
                    <option value="POST" className="text-yellow-400 font-bold">POST</option>
                    <option value="PUT" className="text-blue-400 font-bold">PUT</option>
                    <option value="DELETE" className="text-red-400 font-bold">DELETE</option>
                </select>
                 
                <span className="inline font-bold">Endpoint: </span>
                <input type="text" name="endpoint" id="" placeholder="enter endpoint start with '/'" className="py-1 focus:outline-blue-200 bg-gray-200 rounded-xl px-4 w-130" />

                <p className="mt-5 font-bold">Headers</p>
                <div className="flex gap-10 my-4">
                    <input type="text" placeholder="Header name" className="w-80 px-4 py-2 focus:outline-blue-300 bg-gray-200 rounded-xl"/>
                    <input type="text" placeholder="Header value" className="w-80 px-4 py-2 focus:outline-blue-300 bg-gray-200 rounded-xl"/>
                </div>
                <button
                onClick={addAssertion} 
                className=" py-2 px-6 cursor-pointer font-bold my-4 border-b-2 rounded-xl focus:cursor-progress">
                    Add Header
                </button>

                <p className="font-bold my-4">Request Body</p>
                <textarea name="body" id="" placeholder="write your body in JSON" className="font-mono block w-150 px-3 py-5 border-1 border-green-600 focus:outline-blue-200">
                </textarea>

                <p className="font-bold mt-8">Assertions</p>
                <div id="assertions" className="flex gap-10 my-4">
                    <input list="assertion_list" placeholder="Assertions" className="w-80 px-4 py-2 focus:outline-blue-300 bg-gray-200 rounded-xl" id="assert" />
                    <datalist id="assertion_list">
                        <option value="body has property"/>
                        <option value="status"/>
                        <option value="body has property value"/>
                        <option value="body contains property"/>
                    </datalist>

                    <input type="text" name="expected" id="" placeholder="expected result" className="w-100 focus:outline-blue-200 bg-gray-200 rounded-xl px-4 py-2"/>
                </div>
                <button
                onClick={addAssertion} 
                className=" py-2 px-6 cursor-pointer font-bold my-4 border-b-2 rounded-xl focus:cursor-progress">
                    Add Assertion
                </button>
                <div className="flex justify-end w-3/4 px-4 py-2 mt-2 gap-4">
                    <button className="font-bold border-b-2 hover:border-green-700 hover:text-green-700 rounded-b-xl px-3 py-2 cursor-pointer ">Save Test</button>
                    <button className="font-bold border-b-2 hover:border-red-700 hover:text-red-700 rounded-b-xl px-3 py-2 cursor-pointer ">Delete Test</button>
                </div>
            </div>
        </>
    )
}

export default TestCase;

//     {
//       "name": "Get all products returns 200",
//       "method": "GET",
//       "endpoint": "/products",
//       "headers": null,
//       "body": null,
//       "assertions": [
//         { "assertion": "status", "expected": 220 },
//         { "assertion": "body_has_property", "expected": "products" }
//       ]
//     },