import { useEffect, useState } from "react";
import useSuiteStore from "../store/suiteStore";


function TestCase(){

    const [testCase, setTestCase] = useState({
      name: "",
      method: "GET",
      endpoint: "/",
      headers: {},
      body: {},
      assertions: []
    }
    )
    const [headerNum, setHeaderNum] = useState(0)
    const [showHeaderMod, setShowHeaderMod] = useState(true)
    const [showAssertMod, setShowAssertMod] = useState(true)
    const [header, setHeader] = useState({
        name: "",
        value:""
    })
    const [assertion, setAssertion] = useState({
        assertion:"",
        expected:"",

    })
    const isAssertObj =  assertion.assertion === "body has property value";
    const [isValidJson, setIsValidJson] = useState(true)
    const [invalidJsonMsg, setInvalidJsonMsg] = useState("");
    const [hideHeader, setHideHeader] = useState(false);
    const {addTestCase, suite } = useSuiteStore();
    

    const handleSetHeader =(entry, e)=>{
       if(entry === "name") setHeader({...header, name: e.target.value})
       if(entry === "value") setHeader({...header, value: e.target.value})
       }
    
    const addTestCaseHeader = ()=>{
        if(header.name.length > 0 && header.value.length > 0){
            setTestCase({...testCase, headers:{...testCase.headers, [header.name]:header.value}});
            setHeader({name: "", value:""})
            handleShowMod("header");
        } else {
            alert("Please provide all necessary inputs for the header")
        }
    }

    const handleShowMod = (item) => {
        if(item === "header")setShowHeaderMod(!showHeaderMod);
        if(item === "assertion") setShowAssertMod(!showAssertMod)
    }
    const handleStringInputChange = (property, e)=>{
            setTestCase({...testCase, [property]:e.target.value})
    }

    const handleObjectInputChange = (property, e, assertion) => {
        if(property === "body"){
            let currentValue = e.target.value
            try {
                JSON.parse(currentValue.trim())
                setIsValidJson(true)
                setTestCase({...testCase, body:JSON.parse(currentValue)})
            } catch (error) {
                setIsValidJson(false)
                setInvalidJsonMsg("Please enter valid JSON")
            }
            if(currentValue.length < 1) setIsValidJson(true);
            
        } else if (property === "assertions"){
            setTestCase({...testCase, assertions:[...testCase.assertions, assertion]})
            handleShowMod("assertion")
            setAssertion({assertion:"", expected:""})
        }
    }
    const handleAssertionInputs = (e) => {

        const { name, value } = e.target;
        if (isAssertObj &&(name === "property" || name === "value")
        ) {
            setAssertion(prev => ({...prev,expected: {...prev.expected,[name]: value}}));
        } else {
            setAssertion(prev => ({...prev,[name]: value}));
        }
    }
    const delHeaders =(prop)=> {
        if(window.confirm("Are you sure you want to delete this header?")){
            const updatedHeaders = {...testCase.headers}
            delete updatedHeaders[prop]
            setTestCase({...testCase, headers: updatedHeaders})
        }
    }
    const delAssertions =(index)=> {
        if(window.confirm("Are you sure want to delete this asseriton")){
            const updatedAssertions = testCase.assertions.filter((_,i)=> i !== index)
            setTestCase({...testCase, assertions: updatedAssertions})            
        }
    }
    const methodInputColor=()=> {
        switch (testCase.method) {
            case "GET":
                return "text-green-400 focus:outline-green-400"
                break;
            case "POST":
                return "text-yellow-400 focus:outline-yellow-400"
                break;
            case "PUT":
                return "text-blue-400 focus:outline-blue-400"
                break;
            case "DELETE":
                return "text-red-400 focus:outline-red-400"
                break;
        }
    }
    const setHiddenHead=()=>{
        setHideHeader(!hideHeader)
    }
    const saveTestCase = ()=> {
        const {body,headers, ...rest } = testCase;
        
        if(/^POST|PUT$/.test(testCase.method) && Object.values(testCase).every(a => a.length > 0)){
            addTestCase(testCase)

        } else if(Object.keys(rest).every(a => a.length>0)) {
            addTestCase(testCase)

        } else {
            alert("please fill in the necessary values")            
        }
        console.log(suite)
    }



    return(
        <>
            <div className=" px-8 py-6 rounded-xl">

                <span className="font-bold">Test name: </span>
                <input 
                    type="text" 
                    name="name" 
                    className="block w-100 mb-6 mt-2 focus:outline-blue-300 bg-gray-200 px-4 py-2 rounded-xl" 
                    placeholder="Enter Test name" 
                    onChange={()=> handleStringInputChange("name",event,)}
                    value={testCase.name}
                    />

                <span className="font-bold">Method: </span>
                <select 
                    name="method" 
                    onChange={()=> handleStringInputChange("method",event)} 
                    className={`mr-8 ${methodInputColor()} border-2 text-blue-400 font-bold w-50 py-2 rounded-2xl px-4`}>

                        <option value="GET" className="font-bold text-green-400">GET</option>
                        <option value="POST" className="font-bold text-yellow-400">POST</option>
                        <option value="PUT" className="font-bold text-blue-400">PUT</option>
                        <option value="DELETE" className="font-bold text-red-400">DELETE</option>

                </select>
                 
                <span className="inline font-bold">Endpoint: </span>
                <input type="text" 
                name="endpoint" id="" 
                className="py-1 focus:outline-blue-200 bg-gray-200 rounded-xl px-4" 
                onChange={()=> handleStringInputChange("endpoint", event)}
                value={testCase.endpoint} 
                />

                <div className="flex gap-8 my-8 w-150 items-center">
                    <p className="font-bold">Headers</p> 
                    <img title="Add Header" onClick={()=> handleShowMod("header")} className="w-6 h-6 border-2 hover:cursor-pointer rounded-md" src="src\assets\playlist.png" alt="" />
                </div>

                {Object.keys(testCase.headers).length > 0 &&
                Object.entries(testCase.headers).map(([key, value]) => 
                        <div className="flex gap-6 bg-gray-200 w-fit px-2 py-1 my-1 rounded-lg">
                            <span className="bg-white px-1 font-semibold rounded-lg">{key}:</span>
                            <span className="">{value}</span>
                            <img onClick={()=> delHeaders(key)} title="delete" src="src\assets\remove.png" className="w-5 bg-white rounded-md cursor-pointer" />
                        </div>
                )
                }

                <div id="header_modal" hidden={showHeaderMod} className="fixed inset-0 z-[9999] flex items-center justify-center bg-black/30 backdrop-blur-md">
                    <div className="flex bg-white rounded-md my-4 border-1">
                        <div className="px-8 py-4">
                            <h1 className="mb-8 mt-2 font-bold">Create header</h1>

                            <input type="text" placeholder="Header name" className="w-80 px-4 py-2 focus:outline-blue-300 bg-gray-200 rounded-xl mr-4" value={header.name} onChange={(e)=> handleSetHeader("name",e)}/>
                            <input type={hideHeader?"password":"text"} placeholder="Header value" className="w-80 px-4 py-2 focus:outline-blue-300 bg-gray-200 rounded-xl" value={header.value} onChange={(e)=> handleSetHeader("value",e)}/>
                            {hideHeader?<img src="/src/assets/hide.png" onClick={setHiddenHead} className="w-5" title="show"/>:<img src="/src/assets/show.png" onClick={setHiddenHead} className="w-5" title="hide"/>}   

                            <div className="flex gap-8 justify-end">
                                <button onClick={addTestCaseHeader} type="button" className="bg-black rounded-sm text-gray-400 px-10 my-5">
                                    Add
                                </button>
                                <button onClick={()=> handleShowMod("header")} type="button" className="bg-gray-300 rounded-sm px-10 my-5">
                                    cancel
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                

                <p className="font-bold mt-14">Request Body</p>
                <textarea 
                    required={/^POST|PUT$/.test(testCase.method)}
                    name="body"
                    placeholder="write your body in JSON" 
                    className={`font-mono block w-150 px-3 py-5 border-1 border-green-600 ${isValidJson?"outline-green-600":"outline-red-600"}`}
                    onChange={(e)=>handleObjectInputChange("body", e)}
                    >
                </textarea>
                <p hidden={isValidJson} className="text-red-600 my-4 text-sm">{invalidJsonMsg}</p>

                <div className="flex gap-8 my-8 w-150 items-center">
                    <p className="font-bold">Assertions</p> 
                    <img title="Add Assertion" onClick={()=> handleShowMod("assertion")} className="w-6 h-6 border-2 hover:cursor-pointer rounded-md" src="src\assets\playlist.png" alt="" />
                </div>
                {testCase.assertions.length > 0 &&
                    <div>
                        {
                            testCase.assertions.map((a,i) => {
                                if(a.assertion === "body has property value"){
                                return <div className="flex w-fit gap-4 my-2 px-4 py-1 rounded-lg bg-gray-300">
                                        <span className="bg-blue-200">{a.assertion}</span>
                                        <span>{a.expected.property}</span>
                                        <span>{a.expected.value}</span>
                                        <img onClick={()=> delAssertions(i)} src="src/assets/remove.png" className="w-5 bg-white rounded-lg" />
                                    </div>
                                } else {
                                return <div className="flex w-fit gap-4 my-2 px-4 py-1 rounded-lg bg-gray-200">
                                        <span className="px-2 bg-white font-semibold rounded-lg">{a.assertion}:</span>
                                        <span className="px-2 bg-white rounded-lg">{a.expected}</span>
                                        <img onClick={()=> delAssertions(i)} src="src/assets/remove.png" className="w-5 bg-white rounded-lg cursor-pointer"/>
                                    </div>
                                }
                            })
                        }
                    </div>

                }

                <div hidden={showAssertMod} id="assertions" className="fixed inset-0 z-[9999] flex items-center justify-center bg-black/30 backdrop-blur-md">
                    <div className="bg-white py-6 px-4 rounded-md my-4 border-1">

                        <h1 className="font-bold">Add Assertion</h1>
                        <div className="flex my-4 gap-6">
                            <input 
                                list="assertion_list" 
                                placeholder="Assertion" 
                                name="assertion" onChange={(e)=>{handleAssertionInputs(e)}} 
                                className="w-80 px-4 py-2 focus:outline-blue-300 bg-gray-200 rounded-xl" 
                                id="assert"
                                value={assertion.assertion}
                                />
                            <datalist id="assertion_list">
                                <option value="body has property"/>
                                <option value="status"/>
                                <option value="body has property value"/>
                                <option value="body contains property"/>
                            </datalist>
                            <input 
                                name={isAssertObj?"property":"expected"} 
                                onChange={(e)=> handleAssertionInputs(e)}
                                placeholder={isAssertObj? "Property": "Expected Result"} 
                                className="w-100 focus:outline-blue-200 bg-gray-200 rounded-xl px-4 py-2"
                                value={isAssertObj? assertion.expected.property:assertion.expected}/>

                        </div>
                            {isAssertObj
                                && 
                            <input 
                                name="value"
                                className="w-100 focus:outline-blue-200 bg-gray-200 rounded-xl px-4 py-2" 
                                placeholder="Value"
                                onChange={handleAssertionInputs}
                                value={assertion.expected.value || ""}/>
                                    
                            }    

                        <div className="flex gap-4 justify-end">
                            <button
                            type="button"
                            onClick={(e)=> handleObjectInputChange("assertions",e,assertion)}
                            className=" px-6 hover:bg-green-700 cursor-pointer bg-gray-500 rounded-sm">
                                Add Assertion
                            </button>
                            <button
                            type="button"
                            onClick={()=> handleShowMod("assertion")}
                            className="bg-gray-300 hover:bg-red-700 cursor-pointer rounded-sm px-6">
                                Cancel
                            </button>    
                        </div>
                        
                    </div>
                </div>
                <div className="flex justify-end w-3/4 px-4 py-2 mt-2 gap-4">
                    <button type="button" onClick={saveTestCase} className="font-bold border-b-2 hover:border-green-700 hover:text-green-700 rounded-b-xl px-3 py-2 cursor-pointer ">Save Testcase</button>
                    <button className="font-bold border-b-2 hover:border-red-700 hover:text-red-700 rounded-b-xl px-3 py-2 cursor-pointer ">Cancel</button>
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