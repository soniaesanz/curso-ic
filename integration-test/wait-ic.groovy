def okString = '{"status":"UP"}'
println "waiting IC deploy"
while("curl ${args[0]}/actuator/health".execute().text != okString)
    true
println 'IC deploy complete'