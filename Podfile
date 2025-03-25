platform :ios, '14.0'

project 'iosApp/iosApp.xcodeproj'

target 'iosApp' do
  use_frameworks!

  # Add your dependencies here
  pod 'Alamofire', '~> 5.4'
  pod 'RealmSwift'

  target 'momovenTests' do
    inherit! :search_paths
    # Add specific dependencies for tests here
  end

  target 'momovenUITests' do
    inherit! :search_paths
    # Add specific dependencies for UI tests here
  end
end